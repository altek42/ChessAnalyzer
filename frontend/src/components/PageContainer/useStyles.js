const { makeStyles } = require('@material-ui/core');

export default makeStyles((theme) => ({
  root: {
    height: '100%',
    padding: theme.spacing(2),
    backgroundColor: theme.palette.grey[200],
  },
}));
