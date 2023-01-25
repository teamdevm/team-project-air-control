build:
	docker build -t aircontrol .
build-del:
	docker rmi aircontrol
	docker build -t aircontrol .
run:
	docker run -p 8080:8080 --rm aircontrol
build-del-run:
	docker rmi aircontrol
	docker build -t aircontrol .
	docker run -p 8080:8080 --rm aircontrol