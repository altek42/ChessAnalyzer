'use client'
import { useFormState } from 'react-dom'
import { Button, Paper, Stack, TextField, Typography } from '@mui/material'
import React from 'react'

import { FormState, onFormPostAction } from './Signin.action'

const initialState = {
  message: '',
}

const Signin = () => {
  const [_state, formAction] = useFormState<FormState, FormData>(
    onFormPostAction,
    initialState
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
        <TextField label='Login:' name='login' />
        <TextField label='Password:' name='password' type='password' />
        <Button type='submit' variant='contained'>
          Signin
        </Button>
      </Stack>
    </Paper>
  )
}

export default Signin
