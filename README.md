# Tiny Observability Stack
Building Your First Observability Stack with Open‑Source Tools

## Pre-requisite 
- Docker & docker‑compose installed
- Basic docker compose up / docker compose down familiarity

## Getting Started

### Clone this repository
```shell
git clone git@github.com:ObservabilityHow/tiny-observability-stack.git
```

### Build Tiny App

```shell
docker build -t tiny-app:latest tiny-app
```


### Run Tiny Observability Stack

```shell
docker compose up -d
```

### Stop Tiny Observability Stack

```shell
docker compose down
```
