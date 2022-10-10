export function parseError(xmlString) {
    let parser = new DOMParser();
    let xmlDoc = parser.parseFromString(xmlString, "text/xml");

    return {
        message: xmlDoc.getElementsByTagName("message")[0].childNodes[0].nodeValue,
    }
}
