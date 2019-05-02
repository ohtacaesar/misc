FROM alpine:3.9

ARG ALPINE_SERVER=""
ARG NPM_PROXY=""

RUN set -eux \
    &&  if [[ -n "${ALPINE_SERVER}" ]]; then \
          sed -i "s/dl-cdn.alpinelinux.org/${ALPINE_SERVER}/" /etc/apk/repositories; \
        fi \
    &&  apk add --no-cache nodejs-npm \
    &&  npm install -g serverless \
    &&  cd /root \
    &&  serverless create --template aws-nodejs --path /app

WORKDIR /root/app

CMD ["sh"]
