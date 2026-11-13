
document.addEventListener("DOMContentLoaded", () => {

    const body = document.body;
    const header = document.querySelector("header");
    const navbar = document.querySelector(".navbar");
    const navLinks = document.querySelector(".nav-links");

    // MOBILE MENU
    if (navbar && navLinks && !navbar.querySelector(".menu-toggle")) {
        const menu = document.createElement("button");

        menu.className = "menu-toggle";
        menu.type = "button";
        menu.innerHTML = "☰";
        menu.setAttribute("aria-label", "Open menu");
        menu.setAttribute("aria-expanded", "false");

        navbar.appendChild(menu);

        menu.addEventListener("click", () => {
            const open = navLinks.classList.toggle("is-open");

            menu.innerHTML = open ? "×" : "☰";
            menu.setAttribute("aria-expanded", String(open));
            menu.setAttribute(
                "aria-label",
                open ? "Close menu" : "Open menu"
            );
        });

        navLinks.querySelectorAll("a").forEach(link => {
            link.addEventListener("click", () => {
                navLinks.classList.remove("is-open");
                menu.innerHTML = "☰";
                menu.setAttribute("aria-expanded", "false");
            });
        });

        document.addEventListener("click", event => {
            if (!navbar.contains(event.target)) {
                navLinks.classList.remove("is-open");
                menu.innerHTML = "☰";
                menu.setAttribute("aria-expanded", "false");
            }
        });
    }

    // SCROLL PROGRESS BAR
    const progress = document.createElement("div");
    progress.id = "scroll-progress";
    body.appendChild(progress);

    function updateScroll() {
        const height =
            document.documentElement.scrollHeight - window.innerHeight;

        const percentage = height > 0
            ? (window.scrollY / height) * 100
            : 0;

        progress.style.width = percentage + "%";

        if (header) {
            header.classList.toggle("scrolled", window.scrollY > 25);
        }
    }

    window.addEventListener("scroll", updateScroll, {
        passive: true
    });

    updateScroll();

    // SCROLL REVEAL ANIMATIONS
    const elements = document.querySelectorAll(`
        .section,
        .about-content,
        .interest-card,
        .cv-section,
        .cv-item,
        .blog-post,
        .group-intro,
        .stage-card,
        .skills-intro,
        .skills-section,
        .portfolio-skill,
        .professional-card,
        .project-card,
        .letter-paper,
        .email-paper,
        .resource-section,
        .resource-card,
        .quote-section
    `);

    elements.forEach((element, index) => {
        element.classList.add("reveal");

        if (element.matches(`
            .interest-card,
            .stage-card,
            .portfolio-skill,
            .professional-card,
            .project-card,
            .resource-card
        `)) {
            element.style.transitionDelay =
                (index % 4) * 75 + "ms";
        }
    });

    if ("IntersectionObserver" in window) {
        const observer = new IntersectionObserver(entries => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add("is-visible");
                    observer.unobserve(entry.target);
                }
            });
        }, {
            threshold: 0.12
        });

        elements.forEach(element => observer.observe(element));
    } else {
        elements.forEach(element =>
            element.classList.add("is-visible")
        );
    }

    // PURPLE CURSOR GLOW
    if (
        window.matchMedia("(hover: hover) and (pointer: fine)").matches
    ) {
        const glow = document.createElement("div");
        glow.id = "cursor-glow";
        body.appendChild(glow);

        window.addEventListener("pointermove", event => {
            glow.style.left = event.clientX + "px";
            glow.style.top = event.clientY + "px";
        }, {
            passive: true
        });
    }

    // INTERACTIVE CARD TILT
    if (
        window.matchMedia("(hover: hover) and (pointer: fine)").matches
    ) {
        const cards = document.querySelectorAll(`
            .interest-card,
            .stage-card,
            .portfolio-skill,
            .professional-card,
            .project-card,
            .resource-card
        `);

        cards.forEach(card => {
            card.addEventListener("pointermove", event => {
                const rect = card.getBoundingClientRect();

                const x = (event.clientX - rect.left) / rect.width;
                const y = (event.clientY - rect.top) / rect.height;

                const rotateX = (0.5 - y) * 5;
                const rotateY = (x - 0.5) * 5;

                card.style.transform =
                    `perspective(900px) translateY(-7px)
                    rotateX(${rotateX}deg)
                    rotateY(${rotateY}deg)`;
            });

            card.addEventListener("pointerleave", () => {
                card.style.transform = "";
            });
        });
    }

    // BACK TO TOP
    const topButton = document.querySelector(".back-to-top");

    if (topButton) {
        topButton.addEventListener("click", event => {
            event.preventDefault();

            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });
        });
    }

});
