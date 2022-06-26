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
        rooms.push(new room(i, roomsQuery[0]));

    roomSample = document.getElementsByClassName("room sample");
    roomlst = document.getElementsByClassName("room-lst");
    clonedRoom = roomSample[0].cloneNode(true);
    clonedRoom.classList.remove("sample");

    for(let i = 0; i < rooms.length; i++) {
        clonedRoom.innerHTML += rooms[i].name;
        clonedRoom.id = "room" + i;
        roomlst[0].appendChild(clonedRoom);
    }
}

waitRoom();
