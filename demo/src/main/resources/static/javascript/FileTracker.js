document.addEventListener('DOMContentLoaded', () => {
    const pageEntryTime = Date.now();
    const currentPage = window.location.pathname;

    function updateTimeSpent() {
        const pageExitTime = Date.now();
        const timeSpentOnPage = pageExitTime - pageEntryTime;
        const pageTimes = JSON.parse(localStorage.getItem('pageTimes') || '{}');

        if (pageTimes[currentPage]) {
            pageTimes[currentPage] += timeSpentOnPage;
        } else {
            pageTimes[currentPage] = timeSpentOnPage;
        }

        localStorage.setItem('pageTimes', JSON.stringify(pageTimes));
        const maxTimePage = Object.keys(pageTimes).reduce((a, b) => pageTimes[a] > pageTimes[b] ? a : b);
        console.log(`Page with max time: ${maxTimePage}, Time: ${pageTimes[maxTimePage]}ms`);
    }

    window.addEventListener('beforeunload', updateTimeSpent);
});

function findMostVisitedPage() {
    const pageTimes = JSON.parse(localStorage.getItem('pageTimes') || '{}');
    if (Object.keys(pageTimes).length === 0) {
        return "No page visits tracked yet.";
    }
    const maxTimePage = Object.keys(pageTimes).reduce((a, b) => pageTimes[a] > pageTimes[b] ? a : b);
    const maxTime = pageTimes[maxTimePage];
    const formattedTime = (maxTime / 1000).toFixed(2); // Convert to seconds and format
    return `The most visited page is ${maxTimePage} with a total visit time of ${formattedTime} seconds.`;
}

document.getElementById('showMostVisited').addEventListener('click', () => {
    const result = findMostVisitedPage();
    document.getElementById('mostVisitedResult').innerText = result;
});

console.log("Time tracking script loaded");
