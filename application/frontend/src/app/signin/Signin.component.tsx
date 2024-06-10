'use client'

import { useFormState } from 'react-dom'
import { Button, Paper, Stack, TextField, Typography } from '@mui/material'
import React from 'react'

import { onFormPostAction } from './signin.action'
import { SigninFormType } from './signin.types'

const initialFormState: SigninFormType = {
  loginError: false,
  passwordError: false,
}

const Signin = () => {
  const [state, formAction] = useFormState<SigninFormType, FormData>(
    onFormPostAction,
    initialFormState
  )

  return (
    <Paper
      sx={(theme) => ({
        width: 300,
        margin: theme.spacing(4, 'auto'),
        padding: theme.spacing(3, 2),
      })}
      component={'form'}
      action={formAction}
      noValidate
    >
      <Stack spacing={2}>
        <Typography
          variant='h1'
          sx={{
            fontSize: 'h4.fontSize',
            textAlign: 'center',
          }}
        >
          Signin:
        </Typography>
        <TextField
          required
          label='Login'
          name='login'
          error={state.loginError}
        />
        <TextField
          required
          label='Password'
          name='password'
          type='password'
          error={state.passwordError}
        />
        <Button type='submit' variant='contained'>
          Signin
        </Button>
      </Stack>
    </Paper>
  )
}

export default Signin
