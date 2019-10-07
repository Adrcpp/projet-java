NAME = angular-front

default: build

run:	build
	docker run -it --rm -p 4200:4200 -v $(CURDIR)/app/src:/app/src $(NAME)

build:
	docker build -t $(NAME) .
