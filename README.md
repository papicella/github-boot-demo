# Spring Boot demo using GitHub Actions

This demo hows how to use GitHub Actions from your source code to build and deploy a Spring Boot application 
and push it to Tanzu Application Service for Kubernetes.

## GitHub Action YML

```yaml
name: Java CI with Maven and CD with CF CLI

on:
  push:
    branches: [ master ]
  pull_request:
    branches: [ master ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v2
    - name: Set up JDK 11.0.5
      uses: actions/setup-java@v1
      with:
        java-version: 11.0.5
    - name: Build with Maven
      run: mvn -B package --file pom.xml
    - name: push to TAS4K8s
      env:
        CF_USERNAME: ${{ secrets.CF_USERNAME }}
        CF_PASSWORD: ${{ secrets.CF_PASSWORD }}
      run: |
        curl --location "https://cli.run.pivotal.io/stable?release=linux64-binary&source=github" | tar zx
        ./cf api https://api.tas.lab.pasapples.me --skip-ssl-validation
        ./cf auth $CF_USERNAME $CF_PASSWORD
        ./cf target -o apples-org -s development
        ./cf push -f manifest.yaml
```

<hr size="2" />>
Pas Apicella [pasa at vmware.com] is an Advisory Application Platform Architect at VMware APJ