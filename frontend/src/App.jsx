import Accounts from "./pages/Accounts";
import Orders from "./pages/Orders";
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
            </Stack>
        </Container>
    );
}
