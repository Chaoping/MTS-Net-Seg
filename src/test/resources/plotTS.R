TS = t(read.csv("aq_short.csv", header = F))
rownames(TS) = NULL
Seg = t(read.csv("aq_short_seg.csv", header = F))
require(reshape2)
require(ggplot2)
meltdf = melt(TS)
names(meltdf) = c("Time","Variable","Value")
meltdf$Variable = as.factor(meltdf$Variable)

ts_plot = ggplot(meltdf,aes(x=Time,y=Value,colour=Variable,group=Variable)) + geom_line(size = 1)

for(i in Seg){
  ts_plot = ts_plot + geom_vline(xintercept = i, 
                                      color = "blue", size=0.5)
}

ggsave(filename="myPlot.pdf", plot=ts_plot)