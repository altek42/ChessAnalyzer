'use server'

export type FormState = {
  message: string
}

export async function onFormPostAction(prevState: FormState, data: FormData) {
  console.log(prevState, data)
  return {
    message: 'Form data processed',
  }
}
