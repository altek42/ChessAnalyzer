import TextFieldMui, { TextFieldProps } from '@mui/material/TextField'
import React from 'react'

const TextField = (
  props: TextFieldProps = {
    variant: 'outlined',
  }
) => <TextFieldMui {...props} />

export default TextField
