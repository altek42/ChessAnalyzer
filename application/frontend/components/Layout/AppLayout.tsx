import { Container } from '@mui/material'
import { green } from '@mui/material/colors'
import React, { PropsWithChildren } from 'react'

const AppLayout = ({ children }: PropsWithChildren) => {
  return (
    <Container
      sx={{
        backgroundColor: green[200],
      }}
      maxWidth='sm'
    >
      {children}
    </Container>
  )
}

export default AppLayout
