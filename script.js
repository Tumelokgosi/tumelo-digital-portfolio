
/* =================================
   1. UPDATE COPYRIGHT YEAR
================================= */

const footer = document.querySelector("footer p");

if (footer) {
    footer.innerHTML =
        `&copy; ${new Date().getFullYear()} Tumelo Kgosi Kenosi. All Rights Reserved.`;
}


/* =================================
   2. HIGHLIGHT ACTIVE NAVIGATION LINK
================================= */

const sections = document.querySelectorAll("section[id]");
const navLinks = document.querySelectorAll(".nav-links a");

window.addEventListener("scroll", () => {

    let currentSection = "";

    sections.forEach(section => {

        const sectionTop = section.offsetTop - 150;
        const sectionBottom = sectionTop + section.offsetHeight;

        if (
            window.scrollY >= sectionTop &&
            window.scrollY < sectionBottom
        ) {
            currentSection = section.getAttribute("id");
        }

    });

    navLinks.forEach(link => {

        link.classList.remove("active");

        if (
            link.getAttribute("href") === `#${currentSection}`
        ) {
            link.classList.add("active");
        }

    });

});


/* =================================
   3. BACK TO TOP BUTTON
================================= */

const topButton = document.createElement("button");

topButton.innerHTML = "↑";
topButton.className = "back-to-top";
topButton.setAttribute("aria-label", "Back to top");

document.body.appendChild(topButton);

window.addEventListener("scroll", () => {

    if (window.scrollY > 300) {
        topButton.classList.add("show");
    } else {
        topButton.classList.remove("show");
    }

});

topButton.addEventListener("click", () => {

    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });

});


/* =================================
   4. WELCOME MESSAGE
================================= */

console.log(
    "Welcome to Tumelo Kgosi Kenosi's Digital Portfolio!"
);
