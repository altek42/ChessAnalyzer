import { cookies } from 'next/headers'

import appFetch, { HttpMethod } from '../appFetch'

const signin = async (login: string, password: string) => {
  const { headers } = await appFetch('/authorization/signin', HttpMethod.POST, {
    login,
    password,
  })

  const token = headers.get('Authorization') || ''

  cookies().set('token', token, {
    httpOnly: true,
    secure: true,
  })
}

export default signin
