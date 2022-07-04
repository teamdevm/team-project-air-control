class room {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
};

let addRoomForm = document.querySelector('form[name="addroom"]');
let addSensorForm = document.querySelector('form[name="addsensor"]');

async function getRooms(url) {
    let roomResponse = await fetch(url);
    let roomData = await roomResponse.json();

    rooms = [];

    for(let i = 0; i < roomData.length; i++)
        rooms.push(new room(roomData[i].id, roomData[i].name));

    roomSample = document.getElementsByClassName("room sample");
    roomlst = document.getElementsByClassName("room-lst");

    oldRooms = document.querySelectorAll('.room-lst .room');
    for(let i = 0; i < oldRooms.length; i++) {
        if(!oldRooms[i].classList.contains('sample')) {
            roomlst[0].removeChild(oldRooms[i]);
        }
    }

    for(let i = 0; i < rooms.length; i++) {
        clonedRoom = roomSample[0].cloneNode(true);
        clonedRoom.classList.remove("sample");
        clonedRoom.innerHTML += rooms[i].name;
        clonedRoom.id = "room" + rooms[i].id;
        roomlst[0].appendChild(clonedRoom);
    }

    rooms = document.querySelectorAll('.room-lst .room');
    for(let i = 0; i < rooms.length; i++)
        rooms[i].addEventListener("click", async function(e) {
            selRoom = document.querySelectorAll('.room-lst .selected-room');
            if(selRoom.length > 0)
                selRoom[0].classList.remove('selected-room');
            e.target.classList.add('selected-room');
            let roomName = e.target.textContent;

            let curRoom = document.querySelector('.current-room');
            curRoom.textContent = roomName;

            curRoomId = Number(e.target.id.replace('room', ''));
            path = 'http://localhost:8080/api/v1/roomsinfo/' + curRoomId + '/get/optimalStats';

            let optimalRequest = await fetch(path);
            let optimalData = await optimalRequest.json();

            if (optimalRequest.status > 400 && optimalRequest.status < 600)
                optimalData = {
                    "temperature": -1,
                    "humidity": -1,
                    "co2content": -1
                };
            let optimalStatsEl = document.querySelectorAll('.optimal-chars .char-value');
            optimalStatsEl[0].innerHTML = optimalData.temperature + '°C';
            optimalStatsEl[1].innerHTML = optimalData.humidity + '%';
            optimalStatsEl[2].innerHTML = optimalData.co2content + 'ppm';

            oldSensors = document.querySelectorAll('#block_sensors .block-device');
            sensorLst = document.querySelector('#block_sensors');
            for(let i = 0; i < oldSensors.length; i++) {
                if(!oldSensors[i].classList.contains('sample')) {
                    sensorLst.removeChild(oldSensors[i]);
                }
            }
            await getSensors('http://localhost:8080/api/v1/roomsinfo/' + curRoomId + '/get/sensors');
        });
}

async function getSensors(url) {
    let sensorsResponse = await fetch(url);
    let sensorsData = await sensorsResponse.json();

    sensorSample = document.getElementsByClassName("block-device sample");
    sensorslst = document.getElementsByClassName("#block_sensors");

    sensorsId = []
    curSensors = document.querySelectorAll('#sensors .block-device');
    for(let i = 0; i < curSensors.length; i++) {
        if(!curSensors[i].classList.contains('sample')) {
            sensorsId.push(curSensors[i].id.replace('sensor', ''));
        }
    }

    for(let i = 0; i < sensorsData.length; i++) {
        if(!(Number(sensorsData[i].id) in sensorsId)) {
            clonedSensor = sensorSample[0].cloneNode(true);

            clonedSensor.classList.remove("sample");
            clonedSensor.id = "sensor" + sensorsData[i].id;
            clonedSensor.querySelector('.device-header').innerHTML = sensorsData[i].name;
            clonedSensor.querySelector('p').innerHTML = sensorsData[i].description;

            sensorChars = clonedSensor.querySelectorAll('device-icon');
            if(sensorsData[i].hasTemperature) {
                sensorChars[0].classList.remove('sample');
                sensorChars[0].textContent = Number(sensorsData[i].temperature) + '°C';
            }
            if(sensorsData[i].hasHumidity) {
                sensorChars[1].classList.remove('sample');
                sensorChars[1].textContent = Number(sensorsData[i].humidity) + '%';
            }
            if(sensorsData[i].hasCO2content) {
                sensorChars[2].classList.remove('sample');
                sensorChars[2].textContent = Number(sensorsData[i].co2content) + 'ppm';
            }

            sensorslst.appendChild(clonedSensor);
        }
    }
}

async function addSensor() {
    selRoom = document.querySelector('.room-lst .selected-room');
    curRoomId = selRoom.id.replace('room', '');

    str = 'http://localhost:8080/api/v1/roomsinfo/' + curRoomId + '/add/sensor';

    let data = new FormData(addSensorForm);
    newsensor = {};
    newsensor.name = data.get('sensorName');
    newsensor.description = data.get('sensorDesc');
    if(data.get('sensorTemperature'))
        newsensor.hasTemperature = true;
    if(data.get('sensorHumidity'))
        newsensor.hasHumidity = true;
    if(data.get('sensorCO2'))
        newsensor.hasCO2content = true;

    let sensorRequest = await fetch(str, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newsensor)
    });
    if (roomRequest.status > 400 && roomRequest.status < 600)
        throw 'Ошибочка';
    else {
        await getSensors('http://localhost:8080/api/v1/roomsinfo/' + curRoomId + '/get/sensors');
        stopInterval();
        requestsInterval = setInterval(forInterval, 4000);
    }
}

async function forInterval() {
    let currentStatsEl = document.querySelectorAll('.current-chars .char-value');
    selRoom = document.querySelector('.room-lst .selected-room');

    if(selRoom) {
        curRoomId = selRoom.id.replace('room', '');
        path = 'http://localhost:8080/api/v1/roomsinfo/' + curRoomId + '/get/currentStats';
        let currentRequest = await fetch(path);
        let currentData = await currentRequest.json();

        currentStatsEl[0].innerHTML = currentData.temperature + '°C';
        currentStatsEl[1].innerHTML = currentData.humidity + '%';
        currentStatsEl[2].innerHTML = currentData.co2content + 'ppm';

        path = 'http://localhost:8080/api/v1/roomsinfo/' + curRoomId + '/get/sensors';
        let sensorsRequest = await fetch(path);
        let sensorsData = await sensorsRequest.json();

        for(let i = 0; i < sensorsData.length; i++) {
            let chSensor = document.querySelector('sensor' + sensorsData[i].id);
            if(chSensor) {
                let oldChars = chSensor.querySelectorAll('device-icon');
                if(sensorsData[i].hasTemperature)
                    oldChars[0].textContent =  Number(sensorsData[i].temperature) + '°C';
                if(sensorsData[i].hasHumidity)
                    oldChars[1].textContent =  Number(sensorsData[i].humidity) + '%';
                if(sensorsData[i].hasCO2content)
                    oldChars[2].textContent =  Number(sensorsData[i].co2content) + 'ppm';
            }
        }
    }
}

function stopInterval() {
    clearInterval(requestsInterval);
}

async function addRoom(addForm) {
    let data = new FormData(addForm);
    newroom = {};
    newroom.name = data.get('roomName');
    temper = data.get('optTempreture');
    humid = data.get('optHumidity');
    CO2 = data.get('optCarbon');
    if(temper == '')
        newroom.optimalStats.temperature = 24;
    else
        newroom.optimalStats.temperature = Number(temper);
    if(humid == '')
        newroom.optimalStats.humidity = 40;
    else
        newroom.optimalStats.humidity = Number(humid);
    if(CO2 == '')
        newroom.optimalStats.co2content = 500;
    else
        newroom.optimalStats.co2content = Number(CO2);
    
    str = 'http://localhost:8080/api/v1/roomsinfo/addroom/' + newroom.name;

    let roomRequest = await fetch(str, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newroom.optimalStats)
    });
    if (roomRequest.status > 400 && roomRequest.status < 600)
        throw 'Ошибочка';

    await getRooms('http://localhost:8080/api/v1/roomsinfo/roomList');
}

getRooms('http://localhost:8080/api/v1/roomsinfo/roomList');

let requestsInterval = setInterval(forInterval, 4000);

addRoomForm.addEventListener('submit', async function(e) {
    e.preventDefault();
    try {
        await addRoom(addRoomForm);
    } catch {
        alert("Данные введены некорректно!");
        return;
    }
    
    addRoomForm.reset();
    addRoomForm.closest('.popup').classList.remove('open');
});

addSensorForm.addEventListener('submit', async function(e) {
    e.preventDefault();
    try {
        await addSensor();
    } catch {
        alert("Данные введены некорректно!");
        return;
    }
    
    addSensorForm.reset();
    addSensorForm.closest('.popup').classList.remove('open');
});

