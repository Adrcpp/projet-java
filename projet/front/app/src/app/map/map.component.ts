import { Component, OnInit } from '@angular/core';

import OlMap from 'ol/Map';
import OlXYZ from 'ol/source/XYZ';
import OlTileLayer from 'ol/layer/Tile';
import OlView from 'ol/View';
import { fromLonLat, toLonLat } from "ol/proj";
import Overlay from "ol/Overlay";

import { toStringHDMS } from "ol/coordinate";
declare const $: any;

@Component({
    selector: 'map-root',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.css']
})

export class MapComponent {

    map: OlMap;
    source: OlXYZ;
    layer: OlTileLayer;
    view: OlView;
    marker;
    vienna: Overlay;

    ngOnInit() {

        this.source = new OlXYZ({
            url: 'http://tile.osm.org/{z}/{x}/{y}.png'
        });

        this.layer = new OlTileLayer({
            source: this.source
        });

        this.view = new OlView({
            center: fromLonLat([6.661594, 50.433237]),
            zoom: 16
        });

        this.map = new OlMap({
            target: 'map',
            layers: [this.layer],
            view: this.view
        });

        const pos = fromLonLat([16.3725, 48.208889]);

        this.marker = new Overlay({
            position: pos,
            positioning: "center-center",
            element: document.getElementById("marker"),
            stopEvent: false
        });

        this.map.addOverlay(this.marker);

        this.vienna = new Overlay({
            position: pos,
            element: document.getElementById("vienna")
        });
        this.map.addOverlay(this.vienna);

        const popup = new Overlay({
            element: document.getElementById("popup")
        });
        this.map.addOverlay(popup);

        this.map.on("click", function(evt) {
            const element = popup.getElement();
            const coordinate = evt.coordinate;
            const hdms = toStringHDMS(toLonLat(coordinate));
      
            $(element).popover("dispose");
            popup.setPosition(coordinate);
            $(element).popover({
              placement: "top",
              animation: true,
              html: true,
              content: "<p>The location you clicked was:</p><code>" + hdms + "</code>"
            });
            $(element).popover("show");
          });
    }
}
