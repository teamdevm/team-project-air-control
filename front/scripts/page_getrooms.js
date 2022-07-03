class room {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
};

let addRoomForm = document.querySelector('form[name="addroom"]');

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
        });


};
let currentStatsEl = document.querySelectorAll('.current-chars .char-value');

setInterval(async function() {
    selRoom = document.querySelector('.room-lst .selected-room');

    if(selRoom) {
        curRoomId = selRoom.id.replace('room', '');
        path = 'http://localhost:8080/api/v1/roomsinfo/' + curRoomId + '/get/currentStats';
        let currentRequest = await fetch(path);
        let currentData = await currentRequest.json();

        currentStatsEl[0].innerHTML = currentData.temperature + '°C';
        currentStatsEl[1].innerHTML = currentData.humidity + '%';
        currentStatsEl[2].innerHTML = currentData.co2content + 'ppm';
    }

}, 4000);

getRooms('http://localhost:8080/api/v1/roomsinfo/roomList');

async function addRoom(addForm) {
    let data = new FormData(addForm);
    newroom = {};
    newroom.name = data.get('roomName');
    newroom.optimalStats = {"temperature":Number(data.get('optTempreture')),"humidity":Number(data.get('optHumidity')),"co2content":Number(data.get('optCarbon'))};
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

    getRooms('http://localhost:8080/api/v1/roomsinfo/roomList');
}

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

