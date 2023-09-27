import React from 'react'
import TextField, { TextFieldProps } from '@mui/material/TextField';

export default (props: TextFieldProps = {
    variant: 'outlined'
}) => (
    <TextField {...props} />
)
