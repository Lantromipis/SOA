FROM node:16-alpine as builder

COPY package.json package-lock.json ./

RUN npm install && mkdir /react-ui && mv ./node_modules ./react-ui

WORKDIR /react-ui

COPY . .

RUN npm run build


FROM nginx:alpine

COPY deployment/nginx.conf /etc/nginx/nginx.conf

COPY public /usr/share/nginx/html/public

RUN rm -rf /usr/share/nginx/html/*

COPY --from=builder /react-ui/build /usr/share/nginx/html