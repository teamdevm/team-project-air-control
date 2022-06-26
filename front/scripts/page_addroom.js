let form = document.querySelector('form[name="addroom"]');
newroom = {};

function addRoom(url, data) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    /*xhr.onload = function() {
      let status = xhr.status;
      if (status === 200) {
        roomsQuery = xhr.response;
      } else {
        alert('Something went wrong: ' + err);
      }
    };*/
    xhr.send(JSON.stringify(data));
};

form.addEventListener('submit', e => {
	e.preventDefault();
	let data = new FormData(form);
	newroom.id = 2;
	newroom.name = data.get('roomName');
	newroom.optimalStats = {"temperature":data.get('optTempreture'),"humidity":data.get('optHumidity'),"co2content":data.get('optCarbon')};
	newroom.currentStats = {"temperature":-1,"humidity":-1,"co2content":-1};
	newroom.devices = [];
	newroom.sensors = [];
	newroom.currentCO2content = -1;
	newroom.optimalTemperature = data.get('optTempreture');
	newroom.optimalHumidity = data.get('optHumidity');
	newroom.optimalCO2content = data.get('optCarbon');
	newroom.currentTemperature = -1;
	newroom.currentHumidity = -1;
	alert(JSON.stringify(newroom));

	addRoom('http://localhost:8080/api/v1/roomsinfo', newroom);
	form.reset();
});