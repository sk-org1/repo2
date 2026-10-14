# prudra-pr-policy-test

A test repository for PR (Pull Request) policies with intentional SAST/SCA vulnerabilities for security scanning testing.

## Purpose

This repository contains intentionally vulnerable Java code for testing:
- **SAST** (Static Application Security Testing) tools
- **SCA** (Software Composition Analysis) tools

## Vulnerabilities Included

### SAST Issues
- SQL Injection
- Command Injection
- Path Traversal
- Hardcoded Credentials
- Weak Cryptographic Algorithms (MD5, DES)
- Insecure Random Number Generation

### SCA Issues
- Vulnerable Log4j version (CVE-2021-44228)
- Outdated Spring Framework
- Vulnerable Jackson Databind
- Outdated Commons Collections
- Old MySQL Connector

## Build

```bash
mvn clean compile
```

## Warning

⚠️ This code contains intentional security vulnerabilities for testing purposes only. DO NOT use in production.

PR-TEST-1