# timewise

>An automated tool for scheduling weekly tasks

## Requirements
- Maven
- Java 21
- Docker/Docker Compose
- `make` command

## Getting Started

#### 1. Create a local `.env` file

```
make prepare-env
```

#### 2. Build maven project and container images

```
make build
```

#### 3. Run containers

```
make up
```
