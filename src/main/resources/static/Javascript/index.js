/**
 * Creates a Leaflet map centered on Bretagne, France and adds a tile layer from OpenStreetMap.
 * @type {L.Map} The Leaflet map object.
 */
let map = L.map('mapid').setView([48.2020471, -2.9326435], 8);

// ajouter une couche de tuiles OpenStreetMap à la carte
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: 'Map data © <a href="https://openstreetmap.org">OpenStreetMap</a> contributors',
    maxZoom: 18
}).addTo(map);

/**
 * Fetches festival data from the API and adds markers for each festival to the map.
 */
fetch("http://localhost:8080/api/festivals")
    .then(response => response.json())
    .then(data => {
        // Browse the retrieved data and create a marker for each festival.
        data.forEach(festival => {
            var marker = L.marker([festival.latitude, festival.longitude]).addTo(map);
            var link = "<a href='" + festival.url + "'>" + festival.url + "</a>";
            marker.bindPopup("<b>" + festival.nom + "</b><br>" + festival.ville + "<br>" + festival.lieu + "<br>" + link).openPopup();

            // Add a 'click' event listener to the marker
            marker.on('click', function() {
                // Change the zoom level of the map
                map.setView([festival.latitude, festival.longitude], 12);
            });

        });
    })
    .catch(error => console.error(error));


//-----------------------------------------------------------------------------------------------------//


/**
 * Adds the ability to sort festivals by start date when the "Start date" column header is clicked.
 */
const dateDebutHeader = document.querySelector(".en-tete-tableau:nth-child(4)");

// Add a click event listener to the "Start date" column header.
dateDebutHeader.addEventListener("click", function () {
    const table = document.querySelector(".tableau");
    const tbody = table.querySelector("tbody");
    const rows = Array.from(tbody.querySelectorAll("tr"));

    // Sort data by start date.
    rows.sort(function (a, b) {
        const aDate = new Date(a.querySelector(".cellule:nth-child(4)").innerText);
        const bDate = new Date(b.querySelector(".cellule:nth-child(4)").innerText);

        return aDate - bDate;
    });

    // Reverse the order if the user has already sorted in ascending order.
    if (dateDebutHeader.dataset.sort === "asc") {
        rows.reverse();
        dateDebutHeader.dataset.sort = "desc";
    } else {
        dateDebutHeader.dataset.sort = "asc";
    }

    // Update the table with the sorted data.
    rows.forEach(row => tbody.appendChild(row));
});


//-----------------------------------------------------------------------------------------------------//


/**
 * Adds the ability to sort festivals by name when the "Festival name" column header is clicked.
 */
const nomHeader = document.querySelector(".en-tete-tableau:nth-child(1)");

// Add a click event listener to the header of the "Festival name" column.
nomHeader.addEventListener("click", function () {
    const table = document.querySelector(".tableau");
    const tbody = table.querySelector("tbody");
    const rows = Array.from(tbody.querySelectorAll("tr"));

    // Sorting of the data according to the name of the festival.
    rows.sort(function (a, b) {
        const aNom = a.querySelector(".cellule:nth-child(1)").innerText;
        const bNom = b.querySelector(".cellule:nth-child(1)").innerText;

        return aNom.localeCompare(bNom);
    });

    // Reverse the order if the user has already sorted in ascending order.
    if (nomHeader.dataset.sort === "asc") {
        rows.reverse();
        nomHeader.dataset.sort = "desc";
    } else {
        nomHeader.dataset.sort = "asc";
    }

    // Update the table with the sorted data.
    rows.forEach(row => tbody.appendChild(row));
});


//-----------------------------------------------------------------------------------------------------//


/**
 * Adds the ability to sort festivals by city when the "Festival City" column header is clicked.
 */
const villeHeader = document.querySelector(".en-tete-tableau:nth-child(2)");

// Add a click event listener to the "Festival City" column header.
villeHeader.addEventListener("click", function () {
    const table = document.querySelector(".tableau");
    const tbody = table.querySelector("tbody");
    const rows = Array.from(tbody.querySelectorAll("tr"));

    // Sorting of the data according to the city of the festival.
    rows.sort(function (a, b) {
        const aVille = a.querySelector(".cellule:nth-child(2)").innerText;
        const bVille = b.querySelector(".cellule:nth-child(2)").innerText;

        return aVille.localeCompare(bVille);
    });

    // Reverse the order if the user has already sorted in ascending order.
    if (villeHeader.dataset.sort === "asc") {
        rows.reverse();
        villeHeader.dataset.sort = "desc";
    } else {
        villeHeader.dataset.sort = "asc";
    }

    // Update the table with the sorted data.
    rows.forEach(row => tbody.appendChild(row));
});