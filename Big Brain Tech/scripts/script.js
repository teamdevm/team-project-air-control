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
}