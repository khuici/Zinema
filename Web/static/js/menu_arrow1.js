document.querySelectorAll("li > a.current").forEach(function (a) {
    a.insertAdjacentHTML("afterbegin", '<i class="fa-solid fa-arrow-right"></i> ')
});
