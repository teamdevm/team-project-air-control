window.onload = function() {

    let tabs = document.getElementsByClassName("tab");
    for(let i = 0; i < tabs.length; i++) {
        tabs[i].onclick = function() {changePages(tabs[i], i)};
    }

    let blocks = document.getElementsByClassName("block-devices");

    function changePages(tab, index) {
        if (!tab.classList.contains('active-tab')) {
            for (let i = 0; i < tabs.length; i++) {
                tabs[i].classList.remove('active-tab');
                blocks[i].classList.remove('active-page');
            }
            tabs[index].classList.add('active-tab');
            blocks[index].classList.add('active-page');
        }
    }

    let rooms = document.getElementsByClassName("room");
    let curRoom = document.getElementsByClassName("current-room");
    for(let room of rooms) {
        room.onclick = function() {selectRoom(room)};
    }

    function selectRoom(selected) {
        curRoom[0].innerHTML = selected.innerHTML;
    }
    
    let getJSON = function(url, callback) {
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

    getJSON('http://localhost:8080/api/v1/roomsinfo/roomList',
        function(err, data) {
            if (err !== null) {
                alert('Something went wrong: ' + err);
            } else {
                alert('Your query count: ' + data);
            }
        });
}