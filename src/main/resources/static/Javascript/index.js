// créer une carte centrée sur la Bretagne
let map = L.map('mapid').setView([48.2020471, -2.9326435], 8);

// ajouter une couche de tuiles OpenStreetMap à la carte
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: 'Map data © <a href="https://openstreetmap.org">OpenStreetMap</a> contributors',
    maxZoom: 18
}).addTo(map);

// récupérer les données des festivals et ajoute un pointeur sur la map.
fetch("http://localhost:8080/api/festivals")
    .then(response => response.json())
    .then(data => {
        // Parcourez les données récupérées et créez un marqueur pour chaque festival
        data.forEach(festival => {
            var marker = L.marker([festival.latitude, festival.longitude]).addTo(map);
            marker.bindPopup("<b>" + festival.nom + "</b><br>" + festival.ville + "<br>" + festival.lieu).openPopup();
        });
    })
    .catch(error => console.error(error));

// Obtenir la colonne "Date de début"
const dateDebutHeader = document.querySelector(".en-tete-tableau:nth-child(4)");

// Ajouter un écouteur d'événements de clic à l'en-tête de la colonne "Date de début"
dateDebutHeader.addEventListener("click", function() {
    const table = document.querySelector(".tableau");
    const tbody = table.querySelector("tbody");
    const rows = Array.from(tbody.querySelectorAll("tr"));

    // Tri des données en fonction de la date de début
    rows.sort(function(a, b) {
        const aDate = new Date(a.querySelector(".cellule:nth-child(4)").innerText);
        const bDate = new Date(b.querySelector(".cellule:nth-child(4)").innerText);

        return aDate - bDate;
    });

    // Inverser l'ordre si l'utilisateur a déjà trié dans l'ordre croissant
    if (dateDebutHeader.dataset.sort === "asc") {
        rows.reverse();
        dateDebutHeader.dataset.sort = "desc";
    } else {
        dateDebutHeader.dataset.sort = "asc";
    }

    // Mettre à jour le tableau avec les données triées
    rows.forEach(row => tbody.appendChild(row));
});