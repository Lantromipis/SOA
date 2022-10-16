export function parseHeightSumResponse(xmlString) {
    let parser = new DOMParser();
    let response = parser.parseFromString(xmlString, "text/xml");

    return response.getElementsByTagName("Response")[0].getElementsByTagName("sum")[0]?.childNodes[0]?.nodeValue

}

export function parseCountByNewResponse(xmlString) {
    let parser = new DOMParser();
    let response = parser.parseFromString(xmlString, "text/xml");

    return response.getElementsByTagName("Response")[0].getElementsByTagName("count")[0]?.childNodes[0]?.nodeValue

}

export function parseSponsorRequest(value) {
    const doc = document.implementation.createDocument("", "", null);
    const namedElement = doc.createElement("Request");

    const field = doc.createElement("amount");
    field.innerHTML = value
    namedElement.appendChild(field);

    doc.appendChild(namedElement);

    const serializer = new XMLSerializer();
    return serializer.serializeToString(doc);
}
