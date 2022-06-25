alert(1);

class room {
    constructor(id, name) {
        this.name = name;
    }
};

let getRooms = function(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';
    xhr.onload = function() {
      let status = xhr.status;
      if (status === 200) {
        callback(null, xhr.response);
      } else {
        callback(status, xhr.response);
      }
    };
    xhr.send();
};

getRooms('http://localhost:8080/api/v1/roomsinfo/roomList',
    function(err, data) {
        if (err !== null) {
            alert('Something went wrong: ' + err);
        } else {
            alert(typeof data);
        }
    });