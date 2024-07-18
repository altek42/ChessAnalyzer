'use server'

import { z } from 'zod'
import signin from '@/api/authorization/signin'
import { redirect } from 'next/navigation'

import { SigninFormType } from './signin.types'

const validateForm = (formData: FormData): SigninFormType => {
  return {
    loginError: !z.string().min(2).safeParse(formData.get('login')).success,
    passwordError: !z.string().min(2).safeParse(formData.get('password'))
      .success,
  }
}

export const onFormPostAction = async (
  _prevState: SigninFormType,
  formData: FormData
) => {
  const errorData = validateForm(formData)
  if (errorData.loginError || errorData.passwordError) {
    return errorData
  }

  await signin(formData.getString('login'), formData.getString('password'))

  redirect('/dashboard')
}
