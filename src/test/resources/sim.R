filename <- "series.csv"
fileConn <- file(filename)

simulations = list()
if (file.exists(filename)) file.remove(filename)

for (simulation in 1 : 10) {
    # An ARIMA simulation
    simulated <- arima.sim(list(order = c(1, 1, 0), ar = 0.2), n = 200)
    plot(simulated)
    simulations[[simulation]] <- toString(simulated)
}
lapply(simulations, write, filename, append = TRUE)
close(fileConn)
