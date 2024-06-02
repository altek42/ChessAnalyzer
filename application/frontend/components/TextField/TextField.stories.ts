import type { Meta, StoryObj } from '@storybook/react'
import TextField from '@/components/TextField'

const meta = {
  title: 'Components/TextField',
  component: TextField,
  parameters: {
    layout: 'centered',
  },
  tags: ['autodocs'],
  argTypes: {},
} satisfies Meta<typeof TextField>

export default meta
type Story = StoryObj<typeof meta>

export const Empty: Story = {
  args: {
    label: 'Text',
  },
}

export const Filled: Story = {
  args: {
    label: 'Text',
    value: 'Some value',
  },
}
