// récupérer les valeurs de latitude et longitude du formulaire
const latitudeInput = document.getElementById('latitude');
const longitudeInput = document.getElementById('longitude');
const villeInput = document.getElementById('ville');
const cpInput = document.getElementById('cp');

// créer une carte centrée sur la Bretagne
let map = L.map('mapid').setView([48.2020471, -2.9326435], 8);

// ajouter une couche de tuiles OpenStreetMap à la carte
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: 'Map data © <a href="https://openstreetmap.org">OpenStreetMap</a> contributors',
    maxZoom: 18
}).addTo(map);

// créer un marqueur initial
let marker = L.marker([latitudeInput.value, longitudeInput.value]).addTo(map);

// ajouter un événement de clic sur la carte pour mettre à jour la position du marqueur
map.on('click', function(e) {
    marker.setLatLng(e.latlng);
    latitudeInput.value = e.latlng.lat.toFixed(6);
    longitudeInput.value = e.latlng.lng.toFixed(6);

    // récupérer la ville et le code postal correspondant aux coordonnées
    fetch(`https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${e.latlng.lat}&lon=${e.latlng.lng}`)
        .then(response => response.json())
        .then(data => {
            villeInput.value = data.address.city || data.address.town || '';
            cpInput.value = data.address.postcode || '';
        })
        .catch(error => console.error(error));
});