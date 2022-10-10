export function parseFlatsPageResponseFromXml(xmlString) {
    let parser = new DOMParser();
    let xmlDoc = parser.parseFromString(xmlString, "text/xml");

    let flats = Array.from(xmlDoc.getElementsByTagName("Flat"));

    let parsedFlats = flats.map((flatXml) => {
        const coordinatesXml = flatXml.getElementsByTagName("coordinates")[0]
        return {
            id: flatXml.getElementsByTagName("id")[0].childNodes[0].nodeValue,
            area: flatXml.getElementsByTagName("area")[0].childNodes[0].nodeValue,
            coordinates: {
                x: coordinatesXml.getElementsByTagName("x")[0].childNodes[0].nodeValue,
                y: coordinatesXml.getElementsByTagName("y")[0].childNodes[0].nodeValue
            },
            creationDate: flatXml.getElementsByTagName("creationDate")[0].childNodes[0].nodeValue,
            height: flatXml.getElementsByTagName("height")[0].childNodes[0].nodeValue,
            house: {
                name: "test",
                numberOfFloors: 1,
                numberOfLifts: 2,
                year: 2022
            },
            name: flatXml.getElementsByTagName("name")[0].childNodes[0].nodeValue,
            new: flatXml.getElementsByTagName("new")[0].childNodes[0].nodeValue,
            numberOfRooms: flatXml.getElementsByTagName("numberOfRooms")[0].childNodes[0].nodeValue,
            price: flatXml.getElementsByTagName("price")[0].childNodes[0].nodeValue,
            transport: flatXml.getElementsByTagName("transport")[0].childNodes[0].nodeValue,
        }
    });

    return {
        items: parsedFlats,
        page: parseInt(xmlDoc.getElementsByTagName("page")[0].childNodes[0].nodeValue),
        pageSize: parseInt(xmlDoc.getElementsByTagName("pageSize")[0].childNodes[0].nodeValue),
        totalPages: parseInt(xmlDoc.getElementsByTagName("totalPages")[0].childNodes[0].nodeValue),
        totalCount: parseInt(xmlDoc.getElementsByTagName("totalCount")[0].childNodes[0].nodeValue),
    }
}

function parseFlatFromXML(xmlString) {
    let parser = new DOMParser();
    let xmlDoc = parser.parseFromString(xmlString, "text/xml");
}

export function parseFlatToXML(flat) {
    console.log(flat)
    const doc = document.implementation.createDocument("", "", null);
    const flatElement = doc.createElement("Flat");

    const coordinates = doc.createElement("coordinates");
    const house = doc.createElement("house");

    if (flat) {
        Object.keys(flat).forEach((key) => {
                if (key) {
                    if (!key.includes(".")) {
                        if (flat[key]) {
                            const field = doc.createElement(key);
                            field.innerHTML = flat[key]
                            flatElement.appendChild(field);
                        }
                    } else {
                        if (key.split(".")[0] === "coordinates") {
                            if (flat[key]) {
                                const field = doc.createElement(key.split(".")[1]);
                                field.innerHTML = flat[key]
                                coordinates.appendChild(field);
                            }
                        }
                        if (key.split(".")[0] === "house") {
                            if (flat[key]) {
                                const field = doc.createElement(key.split(".")[1]);
                                field.innerHTML = flat[key]
                                house.appendChild(field);
                            }
                        }
                    }
                }
            }
        )
    }

    flatElement.appendChild(coordinates);
    flatElement.appendChild(house);

    doc.appendChild(flatElement);

    const serializer = new XMLSerializer();
    return serializer.serializeToString(doc);
}