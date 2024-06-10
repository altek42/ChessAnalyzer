import { cookies } from 'next/headers'

const signin = async (login: string, password: string) => {
  const response = await fetch(
    'http://127.0.0.1:7002/api/v1/authorization/signin',
    {
      method: 'POST',
      headers: {
        'content-type': 'application/json;charset=UTF-8',
      },
      body: JSON.stringify({
        login,
        password,
      }),
    }
  )

  if (response.status !== 200) {
    return // error
  }

  const token = response.headers.get('Authorization') || ''

  cookies().set('token', token)

  const data = await response.json()
  console.log(data, token)
}

export default signin
