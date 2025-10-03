const barsButton = document.querySelector(".menu-bars");
const closeButton = document.querySelector(".menu-close");

const menu = document.getElementById("menu");
const overlay = document.getElementById("menu-overlay");

function toggleMenu(open) {
    menu.classList.toggle("active", open);
    overlay.classList.toggle("active", open);
}

barsButton.addEventListener("click", function () { toggleMenu(true); });
closeButton.addEventListener("click", function () { toggleMenu(false); });
overlay.addEventListener("click", function () { toggleMenu(false); });
