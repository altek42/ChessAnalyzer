import { cookies } from 'next/headers'

const BASE_URL = 'http://127.0.0.1:7002/api/v1'

export enum HttpMethod {
  GET = 'GET',
  POST = 'POST',
}

type FetchResponse<ResponseType> = {
  headers: Headers
  response: ResponseType
}

const appFetch = async <BodyType, ResponseType>(
  url: string,
  method: HttpMethod,
  body?: BodyType
): Promise<FetchResponse<ResponseType>> => {
  const token = cookies().get('token')?.value

  const requestheaders = new Headers()
  requestheaders.append('content-type', 'application/json;charset=UTF-8')
  if (token) {
    requestheaders.append('Authorization', token)
  }

  const response = await fetch(BASE_URL + url, {
    method: method,
    headers: requestheaders,
    body: JSON.stringify(body),
  })

  const data = await response.json()
  return {
    headers: response.headers,
    response: data as ResponseType,
  }
}

export default appFetch
