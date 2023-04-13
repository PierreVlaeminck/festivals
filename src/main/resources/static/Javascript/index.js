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