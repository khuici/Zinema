document.querySelectorAll("li > a.current__page").forEach(function (a) {
    a.insertAdjacentHTML("afterbegin", '<i class="fa-solid fa-arrow-right"></i> ')
});
