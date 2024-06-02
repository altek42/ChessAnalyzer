import { Button, Paper, Stack, Typography } from '@mui/material'
import React from 'react'

const Signin = () => (
  <Paper
    sx={(theme) => ({
      width: 300,
      height: 500,
      margin: '0 auto',
      padding: theme.spacing(2),
    })}
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
      <Button variant='contained'>Signin</Button>
    </Stack>
  </Paper>
)

Signin.Layout = 'sign'

export default Signin
