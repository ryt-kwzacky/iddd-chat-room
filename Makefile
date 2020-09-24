SHELL := /bin/bash
.DEFAULT_GOAL := help

### Variables ###
DEV_DB_NAME := iddd_chatroom_dev
TEST_DB_NAME := iddd_chatroom_test

### Functions ###
# Run docker container
define docker_container_up
	docker-compose -f docker-compose.datastore.yml up -d
endef

define initialize_database
	mysql -u root -h 127.0.0.1 -e 'drop database if exists ${1};'
	mysql -u root -h 127.0.0.1 -e 'create database ${1} character set utf8mb4 collate utf8mb4_bin;'
endef

# https://gist.github.com/tadashi-aikawa/da73d277a3c1ec6767ed48d1335900f3
.PHONY: $(shell grep -h -E '^[a-zA-Z_-]+:' $(MAKEFILE_LIST) | sed 's/://')

### Commands ###
# Stop docker containers to develop.
stop-containers:
	docker-compose -f docker-compose.datastore.yml down

# Start docker containers to develop.
start-containers:
	$(call docker_container_up)

#Initialize your development environment like recreating DB, running migration, etc.
init-development-environment:
	$(call docker_container_up)
	$(call initialize_database,$(DEV_DB_NAME))

init-test-environment:
	$(call docker_container_up)
	$(call initialize_database,$(TEST_DB_NAME))
