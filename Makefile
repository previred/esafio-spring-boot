compile:
	@echo "Creando Compile ..."
	@mvn clean compile

run:
	@echo "Levantando proyecto..."
	@mvn spring-boot:run

compile-run: compile run

start:
	@cp .env ./configs/.env
	@go run ./cmd/main.go

.PHONY: test build
