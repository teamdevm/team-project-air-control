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

    const inputs = document.querySelectorAll('input[type=number]');
    Array.from(inputs).forEach(input => {
            const min = +input.min;
            const max = +input.max;

            input.addEventListener('input', (e) => {
                const value = +input.value;
                if (value > max) { input.value = max }
                else if (value < min) { input.value = min }
            }
    )});

    let popupLinks = document.querySelectorAll('.popup-link');
    const body = document.querySelector('body');

    if (popupLinks.length > 0) {
        for (let index = 0; index < popupLinks.length; index++) {
            let popupLink = popupLinks[index];
            popupLink.addEventListener("click", function (e) {
                let popupName = popupLink.getAttribute('href').replace('#', '');
                let curentPopup = document.getElementById(popupName);
                popupOpen(curentPopup);
                e.preventDefault();
            });
        }
    }

    const popupCloseIcon = document.querySelectorAll('.popup-close');
    if(popupCloseIcon.length > 0) {
        for (let index = 0; index < popupCloseIcon.length; index++) {
            const el = popupCloseIcon[index];
            el.addEventListener('click', function (e) {
                popupClose(el.closest('.popup'));
                e.preventDefault();
            });
        }
    }

    function popupOpen(curentPopup) {
        if(curentPopup) {
            curentPopup.classList.add('open');
            curentPopup.addEventListener("click", function (e) {
                if(!e.target.closest('.popup-content')) {
                    popupClose(e.target.closest('.popup'));
                }
            });
        }
    }

    function popupClose(popupActive) {
        popupActive.classList.remove('open');
    }

}