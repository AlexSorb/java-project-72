@import hexlet.code.dto.url.UrlPage

@param UrlPage page

@template.layout.page(
    page = page,
    content = @`
        <div>
            <p> ${page.getUrl().getId()} : ${page.getUrl().getName()}</p>
        </div>
        `
)