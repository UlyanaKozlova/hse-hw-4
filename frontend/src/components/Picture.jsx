import { Card, CardContent, Typography, Stack, Box } from "@mui/material";

export default function Picture() {
    return (
        <Card>
            <CardContent>
                <Stack spacing={3} alignItems="center">
                    <Typography variant="h5" color="primary" align="center">
                        Happy New Year!
                    </Typography>

                    <Box
                        component="img"
                        src="/new_year.gif"
                        sx={{
                            maxWidth: "100%",
                            borderRadius: 2,
                        }}
                    />
                </Stack>
            </CardContent>
        </Card>
    );
}
