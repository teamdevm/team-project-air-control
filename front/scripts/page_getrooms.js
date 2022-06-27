class room {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
};

roomsQuery = null;
async function getRooms(url) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';
    xhr.onload = function() {
      let status = xhr.status;
      if (status === 200) {
        roomsQuery = xhr.response;
      } else {
        alert('Something went wrong: ' + err);
      }
    };
    xhr.send();
};

getRooms('http://localhost:8080/api/v1/roomsinfo/roomList');

function waitRoom() {
    if(roomsQuery === null) {
        setTimeout(waitRoom, 50);
        return;
    }

    rooms = [];

    for(let i = 0; i < roomsQuery.length; i++)
        rooms.push(new room(i, roomsQuery[i]));

    for(let i = 0; i < rooms.length; i++)
        alert(JSON.stringify(rooms[i]));

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
        clonedRoom.id = "room" + i;
        roomlst[0].appendChild(clonedRoom);
    }
}

waitRoom();

let form = document.querySelector('form[name="addroom"]');
newroom = {};

async function addRoom(url, data) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    await xhr.send(JSON.stringify(data));
    return 0;
};

form.addEventListener('submit', async function(e) {
    e.preventDefault();
    let data = new FormData(form);
    newroom.name = data.get('roomName');
    newroom.optimalStats = {"temperature":Number(data.get('optTempreture')),"humidity":Number(data.get('optHumidity')),"co2content":Number(data.get('optCarbon'))};
    str = 'http://localhost:8080/api/v1/roomsinfo/addroom/' + newroom.name;
    await addRoom(str, newroom.optimalStats);
    

    roomsQuery = null;
    getRooms('http://localhost:8080/api/v1/roomsinfo/roomList');
    waitRoom();

    form.reset();
    form.closest('.popup').classList.remove('open');
});
