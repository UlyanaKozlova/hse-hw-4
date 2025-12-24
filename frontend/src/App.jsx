import Accounts from "./components/Accounts";
import Orders from "./components/Orders";
import Picture from "./components/Picture";
import {Container, Stack, Typography} from "@mui/material";

export default function App() {
    return (
        <Container maxWidth="md">
            <Stack spacing={5} sx={{py: 5}}>
                <Typography variant="h3" align="center" color="primary">
                    Online Store
                </Typography>

                <Accounts/>
                <Orders/>
                <Picture/>
            </Stack>
        </Container>
    );
}
