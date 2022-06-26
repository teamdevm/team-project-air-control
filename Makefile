build:
	docker rmi aircontrol
	docker buildx build -t aircontrol .
run:
	docker run -p 8080:8080 --rm aircontrol