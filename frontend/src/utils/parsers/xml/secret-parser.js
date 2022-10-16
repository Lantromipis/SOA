import {parseFlatFromXML} from "./flat-parser";

export function parseCountriesList(xmlString){
    let parser = new DOMParser();
    let xmlDoc = parser.parseFromString(xmlString, "text/xml");

    let countries = Array.from(xmlDoc.getElementsByTagName("Country"));

    let parsedCountries = countries.map((country) => {
        const serializer = new XMLSerializer();

        let parser = new DOMParser();
        let flatXml = parser.parseFromString(serializer.serializeToString(country), "text/xml");

        return  {
            id: flatXml.getElementsByTagName("id")[0]?.childNodes[0]?.nodeValue,
            name: flatXml.getElementsByTagName("name")[0]?.childNodes[0]?.nodeValue,
        }
    });

    console.log(parsedCountries)

    return {
        items: parsedCountries,
    }
}

export function parseHousesList(xmlString){
    let parser = new DOMParser();
    let xmlDoc = parser.parseFromString(xmlString, "text/xml");

    let houses = Array.from(xmlDoc.getElementsByTagName("House"));

    let parsedHouses = houses.map((country) => {
        const serializer = new XMLSerializer();

        let parser = new DOMParser();
        let flatXml = parser.parseFromString(serializer.serializeToString(country), "text/xml");

        return  {
            id: flatXml.getElementsByTagName("id")[0]?.childNodes[0]?.nodeValue,
            name: flatXml.getElementsByTagName("name")[0]?.childNodes[0]?.nodeValue,
            numberOfRooms: flatXml.getElementsByTagName("numberOfRooms")[0]?.childNodes[0]?.nodeValue,
            numberOfFloors: flatXml.getElementsByTagName("numberOfFloors")[0]?.childNodes[0]?.nodeValue,
            year: flatXml.getElementsByTagName("year")[0]?.childNodes[0]?.nodeValue,
        }
    });

    console.log(parsedHouses)

    return {
        items: parsedHouses,
    }
}