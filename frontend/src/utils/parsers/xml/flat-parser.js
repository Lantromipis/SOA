export function parseFlatsPageResponseFromXml(xmlString) {
    let parser = new DOMParser();
    let xmlDoc = parser.parseFromString(xmlString, "text/xml");

    let flats = Array.from(xmlDoc.getElementsByTagName("Flat"));

    let parsedFlats = flats.map((flatXml) => {
        const serializer = new XMLSerializer();
        return parseFlatFromXML(serializer.serializeToString(flatXml));
    });

    return {
        items: parsedFlats,
        page: parseInt(xmlDoc.getElementsByTagName("page")[0].childNodes[0].nodeValue),
        pageSize: parseInt(xmlDoc.getElementsByTagName("pageSize")[0].childNodes[0].nodeValue),
        totalPages: parseInt(xmlDoc.getElementsByTagName("totalPages")[0].childNodes[0].nodeValue),
        totalCount: parseInt(xmlDoc.getElementsByTagName("totalCount")[0].childNodes[0].nodeValue),
    }
}

export function parseFlatFromXML(xmlString) {
    let parser = new DOMParser();
    let flatXml = parser.parseFromString(xmlString, "text/xml");
    const coordinatesXml = flatXml.getElementsByTagName("coordinates")[0]
    const houseXml = flatXml.getElementsByTagName("house")[0]

    let ret = {
        id: flatXml.getElementsByTagName("id")[0]?.childNodes[0]?.nodeValue,
        area: flatXml.getElementsByTagName("area")[0]?.childNodes[0]?.nodeValue,
        creationDate: flatXml.getElementsByTagName("creationDate")[0]?.childNodes[0]?.nodeValue,
        height: flatXml.getElementsByTagName("height")[0]?.childNodes[0]?.nodeValue,
        name: flatXml.getElementsByTagName("name")[0]?.childNodes[0]?.nodeValue,
        new: flatXml.getElementsByTagName("new")[0]?.childNodes[0]?.nodeValue,
        numberOfRooms: flatXml.getElementsByTagName("numberOfRooms")[0]?.childNodes[0]?.nodeValue,
        price: flatXml.getElementsByTagName("price")[0]?.childNodes[0]?.nodeValue,
        transport: flatXml.getElementsByTagName("transport")[0]?.childNodes[0]?.nodeValue,
    }

    if (coordinatesXml) {
        ret = {
            ...ret,
            coordinates: {
                x: coordinatesXml.getElementsByTagName("x")[0]?.childNodes[0]?.nodeValue,
                y: coordinatesXml.getElementsByTagName("y")[0]?.childNodes[0]?.nodeValue
            },
        }
    }

    if (houseXml) {
        ret = {
            ...ret,
            house: {
                name: houseXml.getElementsByTagName("name")[0]?.childNodes[0]?.nodeValue,
                numberOfFloors: houseXml.getElementsByTagName("numberOfFloors")[0]?.childNodes[0]?.nodeValue,
                numberOfLifts: houseXml.getElementsByTagName("numberOfLifts")[0]?.childNodes[0]?.nodeValue,
                year: houseXml.getElementsByTagName("year")[0]?.childNodes[0]?.nodeValue
            },
        }
    }

    return ret;
}

function parseObjectToXML(object, name) {
    const doc = document.implementation.createDocument("", "", null);
    const namedElement = doc.createElement(name);

    Object.keys(object).forEach((key) => {
        if (key) {
            if (typeof object[key] === 'object') {
                if(object[key]) {
                    namedElement.appendChild(parseObjectToXML(object[key], key));
                }
            } else {
                const field = doc.createElement(key);
                field.innerHTML = object[key]
                namedElement.appendChild(field);
            }
        }
    })

    return namedElement;
}

export function parseFlatToXML(flat) {
    const doc = document.implementation.createDocument("", "", null);

    doc.appendChild(parseObjectToXML(flat, "Flat"));

    const serializer = new XMLSerializer();
    return serializer.serializeToString(doc);
}